insert :: [Int] -> Int -> [Int]
insert [] a = [a]
insert (x:xs) a
    | x > a        = a:(x:xs)
    | xs == []     = x:[a]
    | head xs > a  = x:(a:xs)
    | otherwise    = x : (insert xs a)

isort :: [Int] -> [Int]
isort l = isortAux l []
    where
        isortAux l r
            | l == []  = r
            | otherwise     = isortAux (tail l) (insert r (head l))


mymax :: [Int] -> Int
mymax (x:xs)
    | xs == [] || x > y  = x
    | otherwise          = y
    where y = mymax xs

remove :: [Int] -> Int -> [Int]
remove [] _ = []
remove (x:xs) a
    | x == a     = xs
    | otherwise  = x : remove xs a

ssort :: [Int] -> [Int]
ssort l = ssortAux l []
    where
        ssortAux l r
            | l == []  = r
            | otherwise  = ssortAux (remove l (mymax l)) (mymax l : r)


merge :: [Int] -> [Int] -> [Int]
merge [] l = l
merge l [] = l
merge l m
    | head l < head m  = head l : merge (tail l) m
    | otherwise        = head m : merge l (tail m)

msort :: [Int] -> [Int]
msort [] = []
msort [a] = [a]
msort l = merge (msort a) (msort b)
    where (a, b) = splitAt (div (length l) 2) l


genQsort :: Ord a => [a] -> [a]
genQsort [] = []
genQsort (l:ls) = genQsort (filter (\n -> n < l) ls) ++ [l] ++ genQsort (filter (\n -> n >= l) ls)

qsort :: [Int] -> [Int]
qsort = genQsort
