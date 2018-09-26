main = do
    x <- getLine
    if x /= "*" then do
        let temp = words x
        let bmi = ((read (temp!!1))::Double) / ((read (temp!!2))::Double) / ((read (temp!!2))::Double)
        if bmi < 18      then putStrLn (head temp ++ ": underweight")
        else if bmi < 25 then putStrLn (head temp ++ ": normal weight")
        else if bmi < 30 then putStrLn (head temp ++ ": overweight")
        else if bmi < 40 then putStrLn (head temp ++ ": obese")
        else                  putStrLn (head temp ++ ": severely obese")
        main  
    else putStr ""