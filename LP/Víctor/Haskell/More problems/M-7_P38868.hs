merge :: (Int -> Int -> Bool) -> [Int] -> [Int] -> [Int]
merge f [] b = b
merge f a [] = a
merge f (x:xs) (y:ys)
    | f x y     = x:merge f xs (y:ys)
    | otherwise = y:merge f (x:xs) ys

mergesort :: (Int -> Int -> Bool) -> [Int] -> [Int]
mergesort _ [] = []
mergesort _ [a] = [a]
mergesort f l = merge f (mergesort f a) (mergesort f b)
    where (a, b) = div (length l) 2 `splitAt` l
    
minProd :: [Int] -> [Int] -> Int
minProd l1 l2 = foldr (+) 0 $ zipWith (*) (mergesort (<) l1) (mergesort (>) l2)