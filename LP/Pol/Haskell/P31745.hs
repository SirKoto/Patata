--Feu una funció flatten :: [[Int]] -> [Int] que aplana una llista de llistes d’enters en una llista d’enters.
flatten :: [[Int]] -> [Int]
flatten = foldr (++) []

--Feu una funció myLength :: String -> Int que retorna la llargada d’una cadena de caràcters.
myLength :: String -> Int 
myLength = foldr ((+).(const 1)) 0 

--Feu una funció myReverse :: [Int] -> [Int] que inverteix els elements d’una llista d’enters.
myReverse :: [Int] -> [Int]
myReverse = foldl (flip (:) ) []

--Feu una funció countIn :: [[Int]] -> Int -> [Int] que, donada una llista de llistes d’elements ℓ i un element x ens torna la llista que indica quants cops apareix x en cada llista de ℓ.
countIn :: [[Int]] -> Int -> [Int]
countIn l x = map ( foldr (tam) 0) l
    where
        tam :: Int -> Int -> Int
        tam a b 
            | a == x    = 1 + b
            | True      = b

--Feu una funció firstWord :: String -> String que, donat un string amb blancs i caràcacters alfabètics), en retorna la primera paraula.
firstWord :: String -> String
firstWord = (takeWhile (\c -> not $ c == ' ') ) . (dropWhile ( == ' '))