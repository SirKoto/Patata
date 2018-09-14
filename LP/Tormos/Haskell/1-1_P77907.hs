absValue :: Int -> Int
absValue x
    | x < 0     = -x
    | otherwise = x


power :: Int -> Int -> Int
power x p
    | p == 0       = 1
    | p == 1       = x
    | mod p 2 == 0 = (power x (div p 2)) * (power x (div p 2))
    | otherwise    = x * (power x (p-1))


isPrime :: Int -> Bool
isPrime x
    | x == 0    = False
    | x == 1    = False
    | otherwise = not (hasDivisors 2)
    where hasDivisors :: Int -> Bool
          hasDivisors n
              | n * n > x    = False
              | mod x n == 0 = True
              | otherwise    = hasDivisors (n+1)


slowFib :: Int -> Int
slowFib x
    | x == 0 = 0
    | x == 1 = 1
    | otherwise = (slowFib (x-1)) + (slowFib (x-2))


quickFib :: Int -> Int
quickFib x = fst(computeFib x)
    where computeFib x
              | x == 0    = (0, 1)
              | otherwise = (r, r+q)
              where (q, r) = computeFib (x-1)
