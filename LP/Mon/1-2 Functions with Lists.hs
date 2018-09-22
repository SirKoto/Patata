myLength::[Int]->Int
myLength [] = 0
myLength (n:ns) = 1 + myLength ns

myMaximum::[Int]->Int
myMaximum [n] = n
myMaximum (x:xs)
    | x > y     = x
    | otherwise = y
    where 
        y = myMaximum xs
        
average::[Int]->Float
average [n] = fromIntegral n
average (x:xs) = fromIntegral (sumlist (x:xs)) /  fromIntegral (myLength (x:xs))

sumlist::[Int]->Int
sumlist [n] = n
sumlist (x:xs) = x + sumlist (xs)

buildPalindrome::[Int]->[Int]
buildPalindrome l = reverse l ++ l


remove::[Int]->[Int]->[Int]
remove [] ll = []
remove l [] = l
remove (x:xs) (y:ys)
    | hiha (y:ys) x = remove xs (y:ys)
    | otherwise     = [x] ++ remove xs (y:ys)
    where
        hiha::[Int]->Int->Bool
        hiha [] n = False
        hiha (x:xs) n
            | x == n    = True
            | otherwise = hiha xs n
            
flatten::[[Int]]->[Int]
flatten = concat
--flatten [] = []
--flatten (x:xs) = x ++ flatten xs

oddsNevens::[Int]->([Int],[Int])
oddsNevens [] = ([],[])
oddsNevens (x:xs)
    | (x `mod` 2) == 0  = (a,[x]++b)
    |otherwise      = ([x]++a,b)
    where
        (a,b) = oddsNevens xs
        
primeDivisors::Int->[Int]
primeDivisors n
    | n == 1    = []
    | otherwise = getPrimeDivisors n 2
    where 
        getPrimeDivisors::Int->Int->[Int]
        getPrimeDivisors n m
            | n == 1            = []
            | n `mod` m == 0    = [m]++getPrimeDivisors (reduce n m) (m+1)
            | otherwise         = getPrimeDivisors n (m+1)
            where
                reduce::Int->Int->Int
                reduce x y
                    | (x `mod` y) == 0  = reduce (div x y) y
                    | otherwise         = x
