divGetter :: Int -> (Int, [Int])
divGetter 1 = (0, [])
divGetter m 
    | a <= 12   = (a, l)
    | otherwise = (a, [])
    where
        a = fst (nDivGetter x)
        x = powGetter m
        l = init $ genList (foldr (\(a, b) acc -> (take b (repeat a)) ++ acc) [] x)

genList [] = [1]
genList (x:xs) = merge a (map (*x) a)
    where
        a = genList xs
        merge [] l = l
        merge l [] = l
        merge (x:xs) (y:ys)
            | x < y = x:merge xs (y:ys)
            | y < x = y:merge (x:xs) ys
            | otherwise = x:merge xs ys


powGetter :: Int -> [(Int, Int)]
powGetter 1 = []
powGetter m = pGAux 2 m
    where
        pGAux k n
            | n == 1       = []
            | k*k > n      = [(n,1)]
            | mod n k == 0 = (a, b):pGAux (k+1) c
            | otherwise    = pGAux (k+1) n
            where 
                (a, b, c) = reduce (k, 0, n)
                reduce (x, y, z)
                    | mod z x == 0 = reduce (x, y+1, div z x)
                    | otherwise    = (x,y,z)

nDivGetter [] = (0, 0)
nDivGetter ( (a, b):xs ) = (p + b + b*p, r+b)
    where (p, r) = nDivGetter xs

isPseudoPerfect :: [Int] -> Int -> Int -> Bool
isPseudoPerfect [] n acc 
    | n == acc  = True
    | otherwise = False
isPseudoPerfect (d:rdiv) number acc
    | acc > number  =  False
    | acc == number =  True
    | otherwise     =  isPseudoPerfect rdiv number (acc+d) || isPseudoPerfect rdiv number acc


analyze :: Int -> Either Int Bool
analyze n
    | a <= 12   = Right iPP
    | otherwise = Left a
    where
        (a, lst) = divGetter n
        iPP = isPseudoPerfect (reverse lst) n 0