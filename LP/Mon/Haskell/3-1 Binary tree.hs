data Tree a = Node a (Tree a) (Tree a) | Empty 
    deriving (Show)

size::Tree a->Int
size Empty = 0
size (Node x a1 a2) = 1 + (size a1) + (size a2)

height::Tree a->Int
height Empty = 0;
height (Node x a1 a2) = 1 + max (height a1) (height a2)

equal::Eq a => Tree a -> Tree a -> Bool
equal Empty Empty = True
equal Empty _ = False
equal _ Empty = False
equal (Node x a1 a2) (Node y b1 b2) = ( (x==y) && (equal a1 b1) && (equal a2 b2))

isomorphic::Eq a=>Tree a->Tree a->Bool
isomorphic Empty Empty = True
isomorphic Empty _ = False
isomorphic _ Empty = False
isomorphic (Node x a1 a2) (Node y b1 b2) = ((x==y) && (equal a1 b2) && (equal a2 b1))

preOrder::Tree a->[a]
preOrder Empty = []
preOrder (Node x a1 a2) = x:preOrder a1++preOrder a2

postOrder::Tree a->[a]
postOrder Empty = []
postOrder (Node x a1 a2) = (postOrder a1 ++ postOrder a2 ++ [x])

inOrder::Tree a->[a]
inOrder Empty = []
inOrder (Node x a1 a2) = (inOrder a1 ++ x:inOrder a2)

breadthFirst::Tree a->[a]
breadthFirst Empty = []
breadthFirst tree = nivells [tree]
    where
        nivells::[Tree a]->[a]
        nivells [] = []
        nivells (Empty:xs) = nivells xs
        nivells ((Node x a1 a2):xs) = x:(nivells (xs++[a1]++[a2]))
        
build::Eq a=>[a]->[a]->Tree a
build [] [] = Empty
build (po:pos) io = (Node po (build pa ia) (build pb ib))
    where 
        comlist::Eq a=>[a]->[a]->[a]
        comlist [] _ = []
        comlist _ [] = []
        comlist (x:xs) is
            |elem x is = x:(comlist xs is)
            |otherwise = (comlist xs is)
            
        ia = (takeWhile ( /= po) io)
        ib = (tail(dropWhile (/= po) io))
        pa = (comlist pos ia)
        pb = (comlist pos ib)
        
overlap::(a->a->a)->Tree a-> Tree a-> Tree a
overlap _ Empty Empty = Empty
overlap _ Empty (Node x a1 a2) = (Node x a1 a2)
overlap _ (Node x a1 a2) Empty = (Node x a1 a2)
overlap f (Node x a1 a2) (Node y b1 b2) = (Node (f x y) (overlap f a1 b1) (overlap f a2 b2))
        
            
