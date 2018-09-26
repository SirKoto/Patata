diffSqrs :: Integer -> Integer
diffSqrs n = evalPol [0, -2, -3, 2, 3] n `div` 12
    where
        evalPol [k] _    = k
        evalPol (x:xs) n = x + n * evalPol xs n

pythagoreanTriplets :: Int -> [(Int, Int, Int)] 
pythagoreanTriplets n = [(a, b, floor c) | a <- [1..n], b <- [2..n], a < b, let c = sqrt (fromIntegral (a*a + b*b) ), ceiling c == floor c, a+b+floor c == n]

-- everyPythagorasTriple = [ if fa > -fb + fb*sqrt 2.0 && fa < fb + fb*sqrt 2.0 then (abs (a*a - b*b), abs (2*a*b),  a*a+b*b)  else (abs (2*a*b), abs (a*a - b*b),  a*a+b*b) | b <- [2..],  a <- [1..(b-1)], let fa = fromInteger a, let fb = fromInteger b ]

tartaglia :: [[Integer]]
tartaglia = iterate (\l -> zipWith (+) (0:l) $ l++[0]) [1]

sumDigits :: Integer -> Integer
sumDigits d = snd $ until ((==0).fst) (\(a, b) -> (div a 10, b + mod a 10)) (d, 0)

digitalRoot :: Integer -> Integer
digitalRoot = until (< 10) sumDigits