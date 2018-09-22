myFoldl::(a->b->a)->a->[b]->a
myFoldl p x [] = x
myFoldl p x (y:ys) = myFoldl p (p x y) ys 

myFoldr::(a->b->b)->b->[a]->b
myFoldr p x [] = x
myFoldr p x (y:ys) = p y (myFoldr p x ys)

myIterate::(a->a)->a->[a]
myIterate f x = x:(myIterate f (f x))

myUntil::(a->Bool)->(a->a)->a->a
myUntil b f x
    | b x   = x
    |otherwise = myUntil b f (f x)

myMap::(a->b)->[a]->[b]
myMap b = myFoldr ((++).(:[]).b) []

myFilter::(a->Bool)->[a]->[a]
-- devuelve los elementos de x que cumplan b
myFilter b x = myFoldr f [] x
    where 
        f n acc
            | (b n)  = n:acc
            | otherwise = acc

myAll::(a->Bool)->[a]->Bool
myAll b x = and (myMap b x)

myAny::(a->Bool)->[a]->Bool
myAny b x = or (myMap b x)

myZip::[a]->[b]->[(a,b)]
myZip x [] = []
myZip [] y = []
myZip (x:xs) (y:ys) = (x,y):(myZip xs ys)

myZipWith::(a->b->c)->[a]->[b]->[c]
myZipWith f a [] = []
myZipWith f [] a = []
myZipWith f a b = myMap (\(a,b) -> f a b) (myZip a b) --(\(a,b) -> f a b) == uncurry
