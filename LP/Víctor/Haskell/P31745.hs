flatten :: [[Int]] -> [Int]
flatten = foldr (++) []

myLength :: String -> Int
myLength = foldr ((+).(const 1)) 0

myReverse :: [Int] -> [Int]
myReverse = foldl (flip (:)) []

countIn :: [[Int]] -> Int -> [Int]
countIn l n = map (length . filter (== n)) l

firstWord :: String -> String
firstWord = (takeWhile (/=' ')).(dropWhile (==' '))