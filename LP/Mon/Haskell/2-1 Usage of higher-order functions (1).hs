eql::[Int]->[Int]->Bool
eql a b = elem a [b]

prod::[Int]->Int
prod = product

prodOfEvens::[Int]->Int
prodOfEvens n = product (filter even n) 

powersOf2::[Int]
powersOf2 = iterate (* 2) 1

scalarProduct::[Float]->[Float]->Float
scalarProduct n m = foldl (+) 0 (zipWith (*) n m)
