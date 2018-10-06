select :: Ord a => [a] -> Int -> a
select xs i = select2 xs (i-1)

select2 xs i
    | null (drop 5 xs)  = mergesort (<) xs !! i
    | k <= i && i < k+e = pivot
    | i < k             = select2 smaller i
    | otherwise         = select2 larger (i - k - e)
    where
        pivot = medianOfMedians( map medianOf5 (chunksOf5 xs))
        (smaller, equal, larger) = pivotPartition xs pivot
        k = length smaller
        e = length equal
        

pivotPartition xp pivot = (smol, equ, thicc)
    where
       smol  = filter (< pivot)  xp
       equ   = filter (== pivot) xp
       thicc = filter (> pivot)  xp

medianIndex size = (size - 1) `quot` 2

medianOfMedians xk = select2 xk (medianIndex (length xk))

chunksOf5 l = b:a
    where (a, b) = foldr (\n (x, y) -> if (length y == 5) then (y:x, [n]) else (x, n:y) ) ([], []) l

merge f [] b = b
merge f a [] = a
merge f (x:xs) (y:ys)
    | f x y     = x:merge f xs (y:ys)
    | otherwise = y:merge f (x:xs) ys

mergesort _ [] = []
mergesort _ [a] = [a]
mergesort f l = merge f (mergesort f a) (mergesort f b)
    where (a, b) = div (length l) 2 `splitAt` l
    
medianOf5 l = (mergesort (<) l) !! (div (length l) 2)