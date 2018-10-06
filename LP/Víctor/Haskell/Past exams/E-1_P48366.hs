-- Ex 1
eval1 :: String -> Int
eval1 = head.(aux []).words
    where
        aux a [] = a
        aux l (x:xs) = case x of
            "+" -> aux (( (+)  (head $ tail l) (head l) ):(tail $ tail l)) xs
            "-" -> aux (( (-)  (head $ tail l) (head l) ):(tail $ tail l)) xs
            "*" -> aux (( (*)  (head $ tail l) (head l) ):(tail $ tail l)) xs
            "/" -> aux (((div) (head $ tail l) (head l) ):(tail $ tail l)) xs
            x   -> aux ((read x ::Int) :l) xs
            
-- Ex 2
eval2 :: String -> Int
eval2 lx = head $ foldl (\l n -> case n of
                              "+" ->  ( (+)  (head $ tail l) (head l)) :(tail $ tail l)
                              "-" ->  ( (-)  (head $ tail l) (head l)) :(tail $ tail l)
                              "*" ->  ( (*)  (head $ tail l) (head l)) :(tail $ tail l)
                              "/" ->  ((div) (head $ tail l) (head l)) :(tail $ tail l)
                              n   ->  (read n ::Int):l  ) [] (words lx)
-- Ex 3
fsmap :: a -> [a -> a] -> a
fsmap = foldl (flip ($))

-- Ex 4
fromJust :: Maybe a -> a
fromJust (Just a) = a
fromJust Nothing = error ""

isNothing :: Maybe a -> Bool
isNothing Nothing = True
isNothing _ = False

divideNconquer :: (a -> Maybe b) -> (a -> (a, a)) -> (a -> (a, a) -> (b, b) -> b) -> a -> b
divideNconquer base divide conquer problem
    | isNothing bev  = conquer problem (da, db) (divideNconquer base divide conquer da, divideNconquer base divide conquer db)
    | otherwise      = fromJust bev
    where
        bev = base problem
        (da, db) = divide problem
        
baseQ :: ([Int] -> Maybe [Int])
baseQ []  = Just []
baseQ [a] = Just [a]
baseQ _   = Nothing

divideQ :: ([Int] -> ([Int], [Int]))
divideQ (x:xs) = ( filter (< x) xs, (filter (>= x) xs) )

conquerQ :: ([Int] -> ([Int], [Int]) -> ([Int], [Int]) -> [Int])
conquerQ (x:_) _ (a, b) = a ++ x:b

quickSort :: [Int] -> [Int] 
quickSort = divideNconquer baseQ divideQ conquerQ

-- Ex 5
data Racional = Racional Integer Integer
racional :: Integer -> Integer -> Racional
racional a b = Racional (a `div` g) (b `div` g)
    where g = gcd a b

denominador :: Racional -> Integer
denominador (Racional _ b) = b

numerador :: Racional -> Integer
numerador (Racional a _) = a

instance Eq Racional
    where
        (Racional a b) == (Racional c d) = ((a `div` ga) == (c `div` gc)) && ((b `div` ga) == (d `div` gc))
            where
                ga = gcd a b
                gc = gcd c d
    
instance Show Racional
     where show (Racional a b) = (show a ++ "/" ++ show b)
           
           
-- Ex 6
data Tree a = Node a (Tree a) (Tree a)
applytree (a, b) True  (Node x t1 t2) = Node (Racional a (a+b)) (applytree (a, (a+b)) True t1) (applytree (a, (a+b)) False t2)
applytree (a, b) False (Node x t1 t2) = Node (Racional (a+b) b) (applytree ((a+b), b) True t1) (applytree ((a+b), b) False t2)

ratree = Node (Racional 1 1) (applytree (1,1) True ratree) (applytree (1,1) False ratree)
recXnivells :: Tree a -> [a]
recXnivells t = recXnivells' [t]
    where recXnivells' ((Node x fe fd):ts) = x:recXnivells' (ts ++ [fe, fd])
racionals :: [Racional]
racionals = recXnivells ratree