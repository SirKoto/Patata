ones :: [Integer]
nats :: [Integer]
ints :: [Integer]
triangulars :: [Integer]
factorials :: [Integer]
fibs :: [Integer]
primes :: [Integer]
hammings :: [Integer]
lookNsay :: [Integer]
tartaglia :: [[Integer]]

ones = iterate (*1) 1

nats = iterate (+1) 0

ints = map tWFE nats
    where tWFE n = (div (n+1) 2) * ((mod n 2)*2-1)

triangulars = map temp nats
    where temp n = div (n*n + n) 2

factorials = foldr ((:).fst) [] (iterate (func) (1, 0))
    where func (a, b) = (a * (b+1), b+1)

fibs = map temp nats
    where 
        temp 0 = 0
        temp 1 = 1
        temp n = snd ( quickfib (0, 1) (n-1) )
        quickfib (a, b) n
            | n == 0    = (a, b)
            | otherwise = quickfib (b, a+b) (n-1)

primes = iterate temp 2
    where
        temp n
            | n == 2    = next_prime (n+1)
            | otherwise = next_prime (n+2)
        next_prime n
            | is_prime n 2 = n
            | otherwise    = next_prime (n+1)
        is_prime n divisor
            | divisor * divisor > n = True
            | mod n divisor == 0    = False
            | otherwise             = is_prime n (divisor + 1)

hammings = map head (iterate nH [1])
    where
        nH h = (minOf h2 h3 h5):h
            where
                h2 = filter (> n) (map (*2) h)
                h3 = filter (> n) (map (*3) h)
                h5 = filter (> n) (map (*5) h)
                n = (head h)
                minOf xl yl zl
                    | x < y && x < z = x
                    | y < z          = y
                    | otherwise      = z
                    where
                        x = xl!!(length xl - 1)
                        y = yl!!(length yl - 1)
                        z = zl!!(length zl - 1)

lookNsay = iterate (fst.head.(temp (10, 0) [])) 1
    where
        temp :: (Integer, Integer) -> [(Integer, Integer)] -> Integer -> [(Integer, Integer)]
        temp (a, b) res n
            | n == 0    = [foldr myFunc (0, 1) ((a, b):res)]
            | k == a    = temp (a, b+1) res l
            | otherwise = temp (k, 1) algo l
            where
                myFunc :: (Integer, Integer) -> (Integer, Integer) -> (Integer, Integer)
                myFunc (x, 0) (acc, mult) = (acc, mult)
                myFunc (x, n) (acc, mult) = (acc+(10*n+x)*mult, mult*(nlength n) )
                k = mod n 10
                l = div n 10
                algo = (a, b):res
                nlength 0 = 10
                nlength n = 10 * (nlength (div n 10))

tartaglia = iterate (f 0 []) [1]
    where
        f n acc []     = n:acc
        f n acc (x:xs) = f x ((n+x):acc) xs 



