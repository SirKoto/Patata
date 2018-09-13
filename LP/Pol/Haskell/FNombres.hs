--Feu una funció absValue :: Int -> Int que, donat un enter, retorni el seu valor absolut.
absValue :: Int -> Int
absValue a
    | a > 0 = a
    | otherwise = -a
    
--Feu una funció power :: Int -> Int -> Int que, donats un enter x i un natural p, retorni x elevat a p, és a dir, xp.

power :: Int -> Int -> Int
power x 0 = 1
power x p 
    | even p    = y * y
    | otherwise = x * y * y
    where
        y = power x (div p 2)
        
--Feu una funció isPrime :: Int -> Bool que, donat un natural, indiqui si aquest és primer o no.
isPrime :: Int -> Bool
isPrime 1 = False
isPrime 0 = False
isPrime n = nDivi 2
    where nDivi:: Int -> Bool
          nDivi d
              |n < d * d    = True
              |mod n d == 0 = False
              | True        = nDivi (d + 1)

              
--Feu una funció slowFib :: Int -> Int que retorni l’n-èsim element de la sèrie de Fibonacci tot utilitzant l’algorisme recursiu que la defineix (f(0)=0, f(1)=1, f(n)=f(n−1)+f(n−2) per n≥ 2).

slowFib :: Int -> Int
slowFib 0 = 0
slowFib 1 = 1
slowFib n = slowFib (n-1) + slowFib(n-2)


--Feu una funció quickFib :: Int -> Int que retorni l’n-èsim element de la sèrie de Fibonacci tot utilitzant un algorisme més eficient.

quickFib :: Int -> Int
quickFib n = fst (fib n)
    where   fib :: Int -> (Int, Int)
            fib 0   = (0, 1)
            fib n   =
                let (fx,fx1) = fib (div n 2)
                    fxaux   = fx * (2 * fx1 - fx)
                    fx1aux  = fx * fx + fx1 * fx1
                in 
                    if mod n 2 == 0 
                        then  (fxaux, fx1aux)
                        else (fx1aux, fxaux + fx1aux) 
                  
        




                  
