main = do
    x <- getLine
    if (head x == 'A') || (head x == 'a') then putStrLn "Hello!"
    else putStrLn "Bye!"
        