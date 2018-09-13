--Feu una funció insert :: [Int] -> Int -> [Int] que, donada una llista ordenada i un element, insereixi ordenadament el nou element a la llista.
insert :: [Int] -> Int -> [Int]
insert [] x     = [x]
insert [x] y    
    | x > y     = [y,x]
    | True      = [x,y]
insert (a:ax) y
    | y <= a     = y : a : ax
    | True       = a : insert ax y 
--Feu una funció isort :: [Int] -> [Int] que implementi l’algorisme d’ordenació per inserció utilitzant la funció anterior.
isort :: [Int] -> [Int]
isort asd   = isort2 asd []
    where
        isort2 :: [Int] -> [Int] -> [Int]
        isort2 [] y = y
        isort2 (a:ax) y = isort2 ax $ insert y a

--Feu una funció remove :: [Int] -> Int -> [Int] que, donada una llista i un element x, elimini la primera ocurrència de x de la llista. Podeu assumir que l’element sempre és a la llista.
remove :: [Int] -> Int -> [Int]
remove [] y     = []
remove (a:ax) y 
    | a == y    = ax
    | True      = a : remove ax y
--Feu una funció ssort :: [Int] -> [Int] que implementi l’algorisme d’ordenació per selecció utilitzant la funció anterior.
ssort :: [Int] -> [Int]
ssort []    = []
ssort x       = (minElem x (head x) ) : (ssort  (remove x (minElem x (head x))))
    where
        minElem :: [Int] -> Int -> Int
        minElem [] n = n
        minElem (a:ax) n
            | a < n     = minElem ax a
            | True      = minElem ax n 

--Feu una funció merge :: [Int] -> [Int] -> [Int] que, donades dues llistes ordenades, les fusioni per obtenir una llista amb tots els seus elements ordenats.
merge :: [Int] -> [Int] -> [Int]
merge [] []     = []
merge a []      = a
merge [] b      = b
merge (a:ax) (b:bx) 
    | a < b     = a  : merge ax  (b:bx)
    | True      = b  : merge  (a:ax) bx
--Feu una funció msort :: [Int] -> [Int] que implementi l’algorisme d’ordenació per fusió utilitzant la funció anterior.
msort :: [Int] -> [Int]
msort ax
    | length ax < 2     = ax
    | True              = merge (msort $ fst sptd) (msort $ snd sptd)
        where
            l = div (length ax) 2
            sptd = splitAt l ax

--Feu una funció qsort :: [Int] -> [Int] que implementi l’algorisme d’ordenació ràpida.
qsort :: [Int] -> [Int]
qsort []        = []
qsort (a:ax)    = (qsort (filter (\n -> n < a) ax) ++ [a] ++ qsort (filter (\n -> n >= a) ax ))

--Generalitzeu la funció anterior per fer ara una funció genQsort :: Ord a => [a] -> [a] que ordeni llistes de qualsevol tipus.
genQsort :: Ord a => [a] -> [a]
genQsort []        = []
genQsort (a:ax)    = (genQsort (filter (\n -> n < a) ax) ++ [a] ++ genQsort (filter (\n -> n >= a) ax ))