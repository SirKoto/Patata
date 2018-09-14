myFoldl :: (a -> b -> a) -> a -> [b] -> a
myFoldl func acc [] = acc
myFoldl func acc (x:xs) = myFoldl func (func acc x) xs

myFoldr :: (a -> b -> b) -> b -> [a] -> b
myFoldr func acc [] = acc
myFoldr func acc (x:xs) = func x (myFoldr func acc xs)

myIterate :: (a -> a) -> a -> [a]
myIterate func val = val : (myIterate func (func val))

myUntil :: (a -> Bool) -> (a -> a) -> a -> a
myUntil check func acc
    | check acc = acc
    | otherwise = myUntil check func (func acc)

myMap :: (a -> b) -> [a] -> [b]
myMap f l = myFoldr ((:).f) [] l

myFilter :: (a -> Bool) -> [a] -> [a]
myFilter f l = myFoldr temp [] l
    where temp val acc
            | f val     = val:acc
            | otherwise = acc

myAll :: (a -> Bool) -> [a] -> Bool
myAll f l = and (myMap f l)

myAny :: (a -> Bool) -> [a] -> Bool
myAny f l = or (myMap f l)

myZip :: [a] -> [b] -> [(a, b)]
myZip [] l2 = []
myZip l1 [] = []
myZip (x:xs) (y:ys) = (x, y):(myZip xs ys)

myZipWith :: (a -> b -> c) -> [a] -> [b] -> [c]
myZipWith f l1 l2 = myMap nf (myZip l1 l2)
    where nf x = f (fst x) (snd x)
