fib :: Int -> Integer
fib n = head $ head $ expMat [ [1, 1], [0, 1] ] n
    where
        expMat _ 0 = [ [0, 1], [1, 0] ]
        expMat m n
            | mod n 2 == 0 = matmult mm mm
            | otherwise    = matmult m $ matmult mm mm
            where 
                mm = expMat m (div n 2)
                matmult [[b,d],[a,c]] [[f,h], [e,g]] = [[a*f+b*h, c*f+d*h], [a*e+b*g, c*e+d*g]]