multEq :: Int -> Int -> [Int]
multEq a b = iterate (*(a*b)) 1



selectFirst :: [Int] -> [Int] -> [Int] -> [Int]
selectFirst l1 l2 l3 = filter f l1
    where f n
            | ln3 == length l3 && ln2 /= length l2  = True
            | ln2 == length l2                      = False
            | otherwise                             = ln2 < ln3
            where
                ln3 = length $ takeWhile (/= n) l3
                ln2 = length $ takeWhile (/= n) l2

myIterate :: (a -> a) -> a -> [a]
myIterate f x = scanl (\acc _ -> f acc) x (repeat 0)



type SymTab a = String -> Maybe a

empty :: SymTab a
empty = const Nothing

get :: SymTab a -> String -> Maybe a
get = ($)

set :: SymTab a -> String -> a -> SymTab a
set a b c = (\s -> if s == b then Just c else a s)



data Expr a
     = Val a
     | Var String
     | Sum (Expr a) (Expr a)
     | Sub (Expr a) (Expr a)
     | Mul (Expr a) (Expr a)
     deriving Show
eval :: (Num a) => SymTab a -> Expr a -> Maybe a
eval s (Var k) = s k
eval s (Val a) = Just a
eval s (Sum a b) = (eval s a) >>= (\aa -> (eval s b) >>= return.(aa+) ) 
eval s (Sub a b) = (eval s a) >>= (\aa -> (eval s b) >>= return.(aa-) )
eval s (Mul a b) = (eval s a) >>= (\aa -> (eval s b) >>= return.(aa*) )