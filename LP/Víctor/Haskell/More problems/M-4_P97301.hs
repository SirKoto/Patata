fizzBuzz :: [Either Int String]
fizzBuzz = map f [0..]
    where
        keyNums = [(3, "Fizz"), (5, "Buzz")]
        f n 
            | null myWords = Left n
            | otherwise    = Right (concat myWords)
            where myWords = map snd $ filter ( (==0).(mod n).fst ) keyNums