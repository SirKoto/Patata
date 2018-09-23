insert::[Int]->Int->[Int]
insert [] n = [n]
insert (x:xs) n
    | x > n     = [n] ++ (x:xs)
    | otherwise = [x] ++ insert xs n
    
isort::[Int]->[Int]
isort [] = []
isort [n] = [n]
isort (x:xs) = insert (isort xs) x

remove::[Int]->Int->[Int]
remove [m] n = []
remove (x:xs) n
    | x == n    = xs
    | otherwise = [x] ++ remove xs n
    
ssort::[Int]->[Int]
ssort n
    |n == []    = []
    |otherwise  = ssort (remove n (maximum n)) ++ [maximum n]

merge::[Int]->[Int]->[Int]
merge n [] = n
merge [] n = n
merge (x:xs) (y:ys)
    | x < y     = [x] ++ merge xs (y:ys)
    | otherwise = [y] ++ merge (x:xs) ys
    
msort::[Int]->[Int]
msort [] = []
msort [n] = [n]
msort l = merge (msort a) (msort b)
    where (a,b) = splitAt (div (length l) 2) l
          
qsort::[Int]->[Int]
qsort = genQsort
        
genQsort::Ord a=>[a]->[a]
genQsort [] = []
genQsort (x:xs) = genQsort esq ++ [x] ++ genQsort drt
    where 
        esq = filter (< x) xs
        drt = filter (>= x) xs
