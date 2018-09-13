--Feu una funció myLength :: [Int] -> Int que, donada una llista d’enters, calculi la seva llargada.
myLength :: [Int] -> Int
myLength [] = 0
myLength (a:ax) = 1 + myLength ax
--Feu una funció myMaximum :: [Int] -> Int que, donada una llista d’enters no buida, calculi el seu màxim.
myMaximum :: [Int] -> Int
myMaximum (a:ax)= maxList a ax
    where 
        maxList :: Int -> [Int] -> Int
        maxList m [] = m
        maxList m (t:mx)
            | m <  t    = maxList t mx
            | True      = maxList m mx 

--Feu una funció average :: [Int] -> Float que, donada una llista d’enters no buida, calculi la seva mitjana.
average :: [Int] -> Float
average l = fromIntegral (fst (aux 0 0 l)) / fromIntegral(snd (aux 0 0 l))
    where 
        aux :: Int -> Int -> [Int] -> (Int, Int)
        aux a b []      = (a,b)
        aux a b (c:cx)  = aux (a+c) (b+1) cx
--Feu una funció buildPalindrome :: [Int] -> [Int] que, donada una llista, retorni el palíndrom que comença amb la llista invertida.
buildPalindrome :: [Int] -> [Int]
buildPalindrome [] = []
buildPalindrome l = (aux l) ++ l
    where 
        aux::[Int] -> [Int]
        aux (a:[])  = [a]
        aux (a:ax)  = (aux ax)++[a]
--Feu una funció remove :: [Int] -> [Int] -> [Int] que donada una llista d’enters x i una llista d’enters y, retorna la llista x havent eliminat totes les ocurrències dels elements en y.
remove :: [Int] -> [Int] -> [Int]
remove [] y     = []
remove x []     = x
remove x y      = aux x (intersect x y)
    where
        aux::[Int] -> [Int] -> [Int]
        aux (a:[]) grp  =
            if elem a grp then []
            else [a]
        aux (a:ax) grp =
            if elem a grp then (aux ax grp)
            else [a]++(aux ax grp)
        intersect::[Int] -> [Int] -> [Int]
        intersect [] b  = []
        intersect a []  = []
        intersect (a:ax) l
            | elem a l  = a : intersect ax l
            | True      = intersect ax l



--Feu una funció flatten :: [[Int]] -> [Int] que aplana una llista de llistes produint una llista d’elements.
flatten :: [[Int]] -> [Int]
flatten []      = []
flatten [[]]    = []
flatten (l:[])  = l
flatten ([]:lx) = flatten lx
flatten (l:lx)  = l++(flatten lx)

--Feu una funció oddsNevens :: [Int] -> ([Int],[Int]) que, donada una llista d’enters, retorni dues llistes, una que conté els parells i una que conté els senars, en el mateix ordre relatiu que a l’original.
oddsNevens :: [Int] -> ([Int],[Int])
oddsNevens [] = ([],[])
oddsNevens (a:ax) =
    if      not (even a) then ((a:(fst (oddsNevens ax))),(snd (oddsNevens ax)))
    else    ((fst (oddsNevens ax)),(a:(snd (oddsNevens ax))))

    --Feu una funció primeDivisors :: Int -> [Int] que retorni la llista de divisors primers d’un enter estrictament positiu.
primeDivisors :: Int -> [Int]
primeDivisors 0     = []
primeDivisors asd   = borraDup $ primeDivisors2 asd
    where
        primeDivisors2 :: Int -> [Int]
        primeDivisors2 1   = []
        primeDivisors2 x 
            | fact == []    = [x]
            | True          = fact ++ primeDivisors2  (div  x (head  fact))
            where 
                fact::[Int]
                fact  = take 1 (filter (\d -> mod x d == 0) [2 .. x-1])
        borraDup :: [Int] -> [Int]
        borraDup lx = borra lx []
            where
                borra :: [Int] -> [Int] -> [Int]
                borra [] y    = []
                borra [x] y   
                    | x `elem` y    = []
                    | True          = [x]
                borra (a:xx) y
                    | a `elem` y    = borra xx y
                    | a `elem` xx   = a : (borra xx (a:y))
                    | True          = a : (borra xx y)

