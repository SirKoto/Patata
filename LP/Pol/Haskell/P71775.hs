--Feu una funció countIf :: (Int -> Bool) -> [Int] -> Int que, donat un predicat sobre els enters i una llista d’enters, retorna el nombre d’elements de la llista que satisfan el predicat.
--Nota: Aquesta funció d’ordre superior existeix en llenguatges de tractament de fulls de càlcul com ara EXCEL.
countIf :: (Int -> Bool) -> [Int] -> Int
countIf p l = foldr (aux) 0 l
    where
        aux :: Int -> Int -> Int
        aux x y 
            | p x   = y + 1
            | True  = y

--Feu una funció pam :: [Int] -> [Int -> Int] -> [[Int]] que, donada una llista d’enters i una llista de funcions d’enters a enters, retorna la llista de llistes resultant d’aplicar cada una de les funcions de la segona llista als elements de la primera llista.
pam :: [Int] -> [Int -> Int] -> [[Int]]
pam l p = foldr ((:).(flip map) l) [] p 

--Feu una funció pam2 :: [Int] -> [Int -> Int] -> [[Int]] que, donada una llista d’enters i una llista de funcions d’enters a enters, retorna la llista de llistes on cada llista és el resultat d’aplicar successivament les funcions de la segona llista a cada element de la primera llista.
--Nota: Qualsevol semblança amb La parte contratante de la primera parte será considerada como la parte contratante de la primera parte és pura casualitat.
pam2 :: [Int] -> [Int -> Int] -> [[Int]]
{-pam2 l p = map (aux) l
    where
        aux :: Int -> [Int]
        aux a = map p $ take (length p) $ iterate const a --ESTO NO SALEEEEEEEEEEEEEEEEEEEE
-}
pam2 l p = map (aux) l
    where
        aux :: Int -> [Int]
        aux i = foldr ((:).(aux2)) [] p
            where
                aux2 :: (Int -> Int) -> Int
                aux2 r = r i

--Feu una funció filterFoldl :: (Int -> Bool) -> (Int -> Int -> Int) -> Int -> [Int] -> Int que fa el plegat dels elements que satisfan la propietat donada.
filterFoldl :: (Int -> Bool) -> (Int -> Int -> Int) -> Int -> [Int] -> Int
filterFoldl bul p acum lx = foldl aux acum lx
    where 
        aux :: Int -> Int -> Int
        aux x y 
            | bul y   = p x y
            | True    = x  

--Feu una funció insert :: (Int -> Int -> Bool) -> [Int] -> Int -> [Int] que donada una relació entre enters, una llista i un element, ens retorna la llista amb l’element inserit segons la relació.
insert :: (Int -> Int -> Bool) -> [Int] -> Int -> [Int]
insert _ [] x       = [x]
insert cmp (l:lx) x 
    | x `cmp` l     = x : l : lx
    | True          = l : insert cmp lx x 

--Utilitzant la funció insert, feu una funció insertionSort :: (Int -> Int -> Bool) -> [Int] -> [Int] que ordeni la llista per inserció segons la relació donada.
insertionSort :: (Int -> Int -> Bool) -> [Int] -> [Int]
insertionSort cmp lx = foldl (insert cmp) [] lx