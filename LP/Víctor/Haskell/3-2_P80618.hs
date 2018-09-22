data Queue a = Queue [a] [a]
    deriving (Show)

create :: Queue a
create = Queue [] []

push :: a -> Queue a -> Queue a
push k (Queue l m) = Queue l (k:m)

pop :: Queue a -> Queue a
pop (Queue [] m)     = pop (Queue (reverse m) [])
pop (Queue (x:xs) m) = Queue xs m

top :: Queue a -> a
top (Queue [] m)     = last m
top (Queue (x:xs) _) = x

empty :: Queue a -> Bool
empty (Queue [] []) = True
empty _ = False

instance Eq a => Eq (Queue a)
    where
        x == y
            | empty x && empty y = True
            | empty x /= empty y = False
            | otherwise          = (top x == top y) && (pop x == pop y)