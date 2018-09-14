eql :: [Int] -> [Int] -> Bool
eql a b
    | length a /= length b  = False
    | otherwise             = and (zipWith (==) a b)

prod :: [Int] -> Int
prod = foldr (*) 1

prodOfEvens :: [Int] -> Int
prodOfEvens l = foldr (*) 1 (filter even l)

powersOf2 :: [Int]
powersOf2 = iterate ((*) 2) 1

scalarProduct :: [Float] -> [Float] -> Float
scalarProduct a b = foldl (+) 0 (zipWith (*) a b)
