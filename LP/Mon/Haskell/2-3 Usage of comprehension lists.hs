myMap::(a->b)->[a]->[b]
myMap f a = [f s| s <- a]

myFilter::(a->Bool)->[a]->[a]
myFilter f x = [s|s <- x, f s]

myZipWith::(a->b->c)->[a]->[b]->[c]
myZipWith b x y = [b n m|(n,m) <- (zip x y)]

thingify::[Int]->[Int]->[(Int,Int)]
thingify x y = [(a, b)|a <- x,b <- y, (mod a b) == 0]

factors::Int->[Int]
factors x = [a|a <- [1..x],(mod x a) == 0]
