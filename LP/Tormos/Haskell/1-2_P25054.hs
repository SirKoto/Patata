myLength :: [Int] -> Int
myLength [] = 0
myLength (x:xs) = 1 + myLength xs


myMaximum :: [Int] -> Int
myMaximum (x:xs)
    | xs == [] || x > y  = x
    | otherwise          = y
    where y = myMaximum xs


average :: [Int] -> Float
average (x:xs)
    | xs == []   = fromIntegral x
    | otherwise  = ( (fromIntegral x) + (l * y) ) / (1 + l)
    where l = fromIntegral (myLength xs)
          y = average xs


buildPalindrome :: [Int] -> [Int]
buildPalindrome l = reverse l ++ l


remove :: [Int] -> [Int] -> [Int]
remove [] _ = []
remove (x:xs) b
    | elem x b   = remove xs b
    | otherwise  = x : (remove xs b)


flatten :: [[Int]] -> [Int]
flatten [] = []
flatten (x:xs)
    | xs == []   = x
    | otherwise  = x ++ (flatten xs)


oddsNevens :: [Int] -> ([Int],[Int])
oddsNevens [] = ([],[])
oddsNevens (x:xs)
    | mod x 2 == 1  = (x:a, b)
    | otherwise     = (a, x:b)
    where (a,b) = oddsNevens xs


getPrimeDivisors :: Int -> Int -> [Int]
getPrimeDivisors x d
            | x == 1        = []
            | mod x d == 0  = d : (getPrimeDivisors (reduce x d) (d+1))
            | otherwise     = getPrimeDivisors x (d+1)
    where reduce x d
            | mod x d == 0  = reduce (div x d) d
            | otherwise     = x


primeDivisors :: Int -> [Int]
primeDivisors 1 = []
primeDivisors x = getPrimeDivisors x 2
