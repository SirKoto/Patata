countIf::(Int->Bool)->[Int]->Int
countIf n = length.filter n

pam::[Int]->[Int->Int]->[[Int]]
--pam [] _ = []
--pam _ [] = []
pam x f = foldr ((:).(flip map) x ) [] f

pam2::[Int]->[Int->Int]->[[Int]]
--pam2 [] _ = []
--pam2 _ [] = []
pam2 x f = map aux x
    where 
        aux::Int->[Int]
        aux n = foldr ((:).(aux2)) [] f
            where
                aux2::(Int->Int)->Int
                aux2 r = r n

filterFoldl::(Int->Bool)->(Int->Int->Int)->Int->[Int]->Int
filterFoldl b f x l = foldl f x (filter b l)

insert::(Int->Int->Bool)->[Int]->Int->[Int]
insert _ [] x = [x]
insert b (x:xs) n
    | b n x = n:x:xs
    | otherwise = x:(insert b xs n)


insertionSort::(Int->Int->Bool)->[Int]->[Int]
insertionSort b l = insertionSortAux b l []
    where
        insertionSortAux::(Int->Int->Bool)->[Int]->[Int]->[Int]
        insertionSortAux _ [] acc     = acc
        insertionSortAux b (l:ls) acc = insert b (insertionSortAux b ls acc) l
