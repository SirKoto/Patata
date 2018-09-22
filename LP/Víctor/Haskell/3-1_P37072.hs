data Tree a = Node a (Tree a) (Tree a) | Empty deriving (Show)

size :: Tree a -> Int
size Empty = 0
size (Node k a b) = 1 + (size a) + (size b)

height :: Tree a -> Int
height Empty = 0
height (Node k a b) = 1 + max (height a) (height b)

equal :: Eq a => Tree a -> Tree a -> Bool
equal Empty Empty = True
equal Empty _ = False
equal _ Empty = False
equal (Node i a b) (Node j c d) = (i == j) && (equal a c) && (equal b d)
    
isomorphic :: Eq a => Tree a -> Tree a -> Bool
isomorphic Empty Empty = True
isomorphic Empty _ = False
isomorphic _ Empty = False
isomorphic (Node i a b) (Node j c d) = (i == j) && ((isomorphic a c && isomorphic b d) || (isomorphic a d && isomorphic b c))

preOrder :: Tree a -> [a]
preOrder Empty = []
preOrder (Node k a b) = k : preOrder a ++ preOrder b

postOrder :: Tree a -> [a]
postOrder Empty = []
postOrder (Node k a b) = postOrder a ++ postOrder b ++ [k]

inOrder :: Tree a -> [a]
inOrder Empty = []
inOrder (Node k a b) = inOrder a ++ k : inOrder b

breadthFirst :: Tree a -> [a]
breadthFirst = bFaux.(:[])
    where
        bFaux :: [Tree a] -> [a]
        bFaux [] = []
        bFaux (Empty:xs) = bFaux xs
        bFaux ((Node k a b):xs) = k:bFaux (xs ++ [a, b])

build :: Eq a => [a] -> [a] -> Tree a
build [] [] = Empty
build preord inord = Node hd (build pr1 in1) $ build pr2 in2
    where
        hd  = head preord
        in1 = takeWhile (/= hd) inord
        in2 = tail $ dropWhile (/= hd) inord
        pr1 = filter (`elem` in1) preord
        pr2 = filter (`elem` in2) preord

overlap :: (a -> a -> a) -> Tree a -> Tree a -> Tree a 
overlap _ Empty b = b
overlap _ a Empty = a
overlap f (Node k a b) (Node l c d) = Node (f k l) (overlap f a c) (overlap f b d)