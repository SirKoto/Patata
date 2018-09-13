--només podeu utilitzar recursivitat per definir myFoldl, myFoldr, myIterate, myUntil i myZip.
myFoldl :: (a -> b -> a) -> a -> [b] -> a
myFoldl p ac [] =  ac
myFoldl p ac (l:lx)  = myFoldl p (p ac l) lx

myFoldr :: (a -> b -> b) -> b -> [a] -> b
myFoldr p ac []     = ac
myFoldr p ac (l:[]) = p l ac
myFoldr p ac (l:lx) = p l $ myFoldr p ac lx

myIterate :: (a -> a) -> a -> [a]
myIterate p n = n :( myIterate p $ p n )

myUntil :: (a -> Bool) -> (a -> a) -> a -> a
myUntil cmp p ac 
    | cmp ac    = ac
    | True      = myUntil cmp p $ p ac

myMap :: (a -> b) -> [a] -> [b]
myMap p l = foldr ((:).(\d -> p d)) [] l

myFilter :: (a -> Bool) -> [a] -> [a]
myFilter p l = foldr ((++).(\d -> if p d then [d] else [] )) [] l

myAll :: (a -> Bool) -> [a] -> Bool
myAll p l = and $ map p l

myAny :: (a -> Bool) -> [a] -> Bool
myAny p l = or $ map p l

myZip :: [a] -> [b] -> [(a, b)]
myZip [] b              = []
myZip a []              = []
--myZip (a:[]) (b:bx)          = [(a,b)]
myZip (a:ax) (b:bx) = (a,b) : myZip ax bx 

myZipWith :: (a -> b -> c) -> [a] -> [b] -> [c]
myZipWith p zu xu = foldr  ((:).(\d -> p (fst d) (snd d) ))  [] $ zip zu xu