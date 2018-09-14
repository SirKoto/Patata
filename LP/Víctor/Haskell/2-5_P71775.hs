countIf :: (Int -> Bool) -> [Int] -> Int
countIf f l = length ( filter f l )

pam :: [Int] -> [Int -> Int] -> [[Int]]
pam ln lf = foldr mF [] lf
    where mF v1 a1 = (foldr mF2 [] ln):a1
            where mF2 v2 a2 = (v1 v2):a2

pam2 :: [Int] -> [Int -> Int] -> [[Int]] 
pam2 ln lf = foldr mF [] ln
    where mF v1 a1 = (foldr mF2 [] lf):a1
            where mF2 v2 a2 = (v2 v1):a2

filterFoldl :: (Int -> Bool) -> (Int -> Int -> Int) -> Int -> [Int] -> Int
filterFoldl cond func acc eval = foldl func acc (filter cond eval)

insert :: (Int -> Int -> Bool) -> [Int] -> Int -> [Int]
insert cond list val = (takeWhile (not.cond val) list) ++ [val] ++ (dropWhile (not.cond val) list)

insertionSort :: (Int -> Int -> Bool) -> [Int] -> [Int] 
insertionSort cond list = foldr (flip (insert cond)) [] list
