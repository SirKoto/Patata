collength 1 = 1
collength n = 1 + if (mod n 2) == 0 then collength (div n 2) else collength (3*n+1)

trampotas_locas = [1, 2, 3, 6, 7, 9, 18, 25, 27, 54, 73, 97, 129, 171, 231, 313, 327, 649, 703, 871, 1161, 2223, 2463, 2919, 3711, 6171, 10971, 13255, 17647, 23529, 26623, 34239, 35655, 52527, 77031, 106239, 142587, 156159, 216367, 230631, 410011, 511935, 626331, 837799]

merge f [] b = b
merge f a [] = a
merge f (x:xs) (y:ys)
    | f x y     = x:merge f xs (y:ys)
    | otherwise = y:merge f (x:xs) ys

mergesort _ [] = []
mergesort _ [a] = [a]
mergesort f l = merge f (mergesort f a) (mergesort f b)
    where (a, b) = div (length l) 2 `splitAt` l

nTL n = nTLaux trampotas_locas 0 n
    where nTLaux (x:xs) prev n = if x > n then prev else nTLaux xs x n



serieCollatz :: Integer -> [Integer]
serieCollatz = (++[1]).takeWhile (/= 1).iterate (\n -> if even n then div n 2 else n*3+1)

-- Aquí he hecho trampotas locas xddd
collatzMesLlarga :: Integer -> Integer
collatzMesLlarga 1 = 1
collatzMesLlarga n = if n < 837799 then collength (nTL n) else cMLaux n
    where
        cMLaux 837799 = collength 837799
        cMLaux n      = max (collength n) (cMLaux (n-1))


representantsCollatz :: [Integer] -> [Integer]
representantsCollatz = fst.foldr (\n (a, b) -> if snd n == b then (a, b) else (fst n:a, snd n)) ([], -1).mergesort (\a b -> snd a < snd b).map (\n -> (n, collength n)).mergesort (<)

classeCollatz :: Integer -> Either Int [Integer] 
classeCollatz n = if x > 35 then Left x else Right (mergesort (<) y)
    where
        x = collength n
        y = nit (f []) (x-1) [1]
        nit _ 0 l = l
        nit func n l = nit func (n-1) (func l)
        f exs [] = []
        f exs (l:ls) = ex ++ f (exs ++ ex) ls
            where 
                ex = if mod (l-1) 3 == 0 && mod ld3 2 /= 0 && l > 4 then [ ld3, 2*l ] else [2*l]
                ld3 = div (l-1) 3