data Expr = Val Int | Add Expr Expr | Sub Expr Expr | Mul Expr Expr | Div Expr Expr

eval1 :: Expr -> Int
eval1 (Val n) = n
eval1 (Add a b) = eval1 a   +   eval1 b
eval1 (Sub a b) = eval1 a   -   eval1 b
eval1 (Mul a b) = eval1 a   *   eval1 b
eval1 (Div a b) = eval1 a `div` eval1 b

eval2 :: Expr -> Maybe Int
eval2 (Val n)   = Just n
eval2 (Add a b) = case (eval2 a, eval2 b) of
    (Just a, Just b) -> Just (a+b)
    (Nothing, _)     -> Nothing
    (_, Nothing)     -> Nothing
eval2 (Sub a b) = case (eval2 a, eval2 b) of
    (Just a, Just b) -> Just (a-b)
    (Nothing, _)     -> Nothing
    (_, Nothing)     -> Nothing
eval2 (Mul a b) = case (eval2 a, eval2 b) of
    (Just a, Just b) -> Just (a*b)
    (Nothing, _)     -> Nothing
    (_, Nothing)     -> Nothing
eval2 (Div a b) = case (eval2 a, eval2 b) of
    (Just a, Just 0) -> Nothing
    (Just a, Just b) -> Just (div a b)
    (Nothing, _)     -> Nothing
    (_, Nothing)     -> Nothing

eval3 :: Expr -> Either String Int
eval3 (Val n)   = Right n
eval3 (Add a b) = case (eval3 a, eval3 b) of
    (Right a, Right b) -> Right (a+b)
    (Left k, _)        -> Left k
    (_, Left k)        -> Left k
eval3 (Sub a b) = case (eval3 a, eval3 b) of
    (Right a, Right b) -> Right (a-b)
    (Left k, _)        -> Left k
    (_, Left k)        -> Left k
eval3 (Mul a b) = case (eval3 a, eval3 b) of
    (Right a, Right b) -> Right (a*b)
    (Left k, _)        -> Left k
    (_, Left k)        -> Left k
eval3 (Div a b) = case (eval3 a, eval3 b) of
    (Right a, Right 0) -> Left "div0"
    (Right a, Right b) -> Right (div a b)
    (Left k, _)        -> Left k
    (_, Left k)        -> Left k




