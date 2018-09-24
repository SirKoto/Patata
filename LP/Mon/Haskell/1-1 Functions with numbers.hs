absValue::Int->Int
absValue n
    |   n < 0     = -1 * n
    |   otherwise = n
    
power::Int->Int->Int
power x 0 = 1
power x p
    | even p = y * y
    | otherwise = x * y * y
    where 
        y = power x (div p 2)

isPrime::Int->Bool
isPrime n
    | n < 2     = False
    |otherwise  = not(teDivisors 2)
    where
        teDivisors::Int->Bool
        teDivisors d
            | d * d > n         = False
            | (mod n d) == 0    = True
            | otherwise         = teDivisors (d+1)
            
slowFib::Int->Int
slowFib 0 = 0
slowFib 1 = 1
slowFib n = slowFib(n-1) + slowFib(n-2)

quickFib::Int -> Int
quickFib n = fst (fib n)
    where
        fib 0 = (0, 1)
        fib 1 = (1, 1)
        fib n = (seg, seg + res)
            where (res, seg) = fib (n-1)
