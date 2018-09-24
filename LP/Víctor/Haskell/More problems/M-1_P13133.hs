-- Esta función es poco elegante, pero de tiempo constante, cosa que mola mogollón.
sumMultiples35 :: Integer -> Integer
sumMultiples35 n = mySum 3 + 5*f( g (div n1 5) )
    where 
        n1 = n-1
        mySum k = k * (div n1 k) * ((div n1 k)+1) `div` 2
        f k = div (3*k*(k+1) - 3*k + (mod k 2)) 4
        g k = k - (div k 3)

-- Fácil, sencillo, y para toda la familia.
fibonacci :: Int -> Integer
fibonacci 0 = 0
fibonacci n = auxonacci (1,0,n)
    where auxonacci (a,b,c) = if c <= 1 then a else auxonacci (a+b, a, c-1)

-- Este no pide gran cosa.
sumEvenFibonaccis :: Integer -> Integer
sumEvenFibonaccis n = sum $ takeWhile (<n) (map fst (iterate (\(a, b) -> (a+a+a+b+b, a+a+b)) (2, 1)))

-- Por algún motivo, según este ejercicio, el número 1 es primo.
largestPrimeFactor :: Int -> Int
largestPrimeFactor 1 = 1
largestPrimeFactor n = aux n 2
    where
        aux 1 d = d
        aux n d
            | mod n d == 0 = aux (div n d) d
            | otherwise    = aux n (d+1)

-- PUTA MIERDA DE EJERCICIO JODER
isPalindromic :: Integer -> Bool
isPalindromic n = isPalindrome' $ show n
    where isPalindrome' xs = foldl (\acc (a,b) -> if a == b then acc else False) True $ zip xs (reverse xs)