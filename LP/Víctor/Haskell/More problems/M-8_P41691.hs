mult :: [Double] -> [Double] -> [Double]
mult a b= (mult2 a b)++[0]


mult2 [] [] = []
mult2 [a] [b] = [a*b]
mult2 [a, b] [c, d] = [a*c, a*d + b*c, b*d]
mult2 m1 m2 = sum z0 $ tilt l (sum z1 $ tilt l z2)
    where
        (a, b) = splitAt l m1
        (c, d) = splitAt l m2

        z2 = mult2 b d
        z0 = mult2 a c
        z1 = sub (sub (mult2 (sum a b) (sum c d)) z2) z0

        sum [] l = l
        sum l [] = l
        sum (x:xs) (y:ys) = (x+y):sum xs ys

        sub [] l = map (*(-1)) l
        sub l [] = l
        sub (x:xs) (y:ys) = (x-y):sub xs ys

        l = length m1 `div` 2

        tilt 0 lst = lst
        tilt x lst = 0:tilt (x-1) lst