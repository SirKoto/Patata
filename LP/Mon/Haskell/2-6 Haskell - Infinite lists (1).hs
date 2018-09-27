ones::[Integer]
ones = repeat 1

nats::[Integer]
nats = iterate (+1) 0

ints::[Integer]
ints = map (\n -> (div (n+1) 2) * ((mod n 2)*2-1)) nats

triangulars::[Integer]
triangulars = map (\n -> div (n*(n+1)) 2) nats 

factorials::[Integer]
factorials = scanl (*) 1 (tail nats) 


fibs::[Integer]
fibs = foldr ((:).fst) [] (iterate (\(a,b) -> (b, a+b)) (0,1))

primes::[Integer]
primes = filter isPrime nats
    where
        isPrime::Integer->Bool
        isPrime n
            | n < 2     = False
            |otherwise  = not(teDivisors 2)
            where
                teDivisors::Integer->Bool
                teDivisors d
                    | d * d > n         = False
                    | (mod n d) == 0    = True
                    | otherwise         = teDivisors (d+1)

lookNsay::[Integer]
lookNsay = 1:(iterate (say 1 0) 11)
    where 
        say::Integer->Integer->Integer->Integer
        say contador numeral n
            |numeral == 0           = say 1 (mod n 10) (div n 10)
            |n == 0                 = contador*10+numeral
            |numeral == (mod n 10)  = say (contador+1) numeral (div n 10)
            |otherwise              = (say 1 (mod n 10) (div n 10))*100+ contador*10+numeral

                                
hammings::[Integer]
hammings = 1:(merge (merge (map (*2) hammings) (map (*3) hammings)) (map (*5) hammings))
    where
        merge::[Integer]->[Integer]->[Integer]
        merge n [] = n
        merge [] n = n
        merge (x:xs) (y:ys)
            | x < y = x:(merge xs (y:ys))
            | x == y = x:(merge xs ys)
            | otherwise = y:(merge (x:xs) ys)
            
tartaglia::[[Integer]]
tartaglia = [1]:(iterate ((1:).f) [1,1])
    where
        f::[Integer]->[Integer]
        f (x:xs)
            | xs == []  = [1]
            | otherwise = (x+(head xs)):(f xs)
