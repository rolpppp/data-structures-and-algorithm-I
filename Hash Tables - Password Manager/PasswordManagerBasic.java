import java.util.LinkedList;

class PasswordManagerBasic implements PasswordManager {
    private LinkedList<Password>[] hashTable;
    private int size;
    
    

    @SuppressWarnings("unchecked")
    public PasswordManagerBasic(int size) {
        this.size = size;
        hashTable = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            hashTable[i] = new LinkedList<>();
        }
    }

    public boolean isEmpty(){
        if (hashTable.length == 0){
            return true;
        }else{
            return false;
        }
    }
    
    private int hash(int key) {
        return key % size;
    }

    @Override
    public void insertPassword(Password p) {
        int index = hash(p.getKey());
        hashTable[index].add(p);
    }

    @Override
    public Password deletePassword(int key) {
        int index = hash(key);
        LinkedList<Password> list = hashTable[index];
        for (Password p : list) {
            if (p.getKey() == key) {
                list.remove(p);
                return p;
            }
        }
        return null;
    }

    @Override
    public Password searchPassword(int key) {
        int index = hash(key);
        LinkedList<Password> list = hashTable[index];
        for (Password p : list) {
            if (p.getKey() == key) {
                return p;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setPasswordManagerSize(int size) {
        // This method would typically involve resizing the hash table,
        // which is a complex operation. For simplicity, we'll just
        // create a new hash table with the new size.
        LinkedList<Password>[] oldTable = hashTable;
        this.size = size;
        hashTable = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            hashTable[i] = new LinkedList<>();
        }
        
        // Rehash all existing passwords
        for (LinkedList<Password> list : oldTable) {
            for (Password p : list) {
                insertPassword(p);
            }
        }
    }

    @Override
    public void displayPasswordManager() {
        for (int i = 0; i < size; i++) {
            System.out.println("Index " + i + ":");
            for (Password p : hashTable[i]) {
                System.out.println("  Key: " + p.getKey() + ", Value: " + p.getValue());
            }
        }
    }

    public LinkedList<Password>[] getHashTable() {
        return hashTable;
    }

    public void setHashTable(LinkedList<Password>[] hashTable) {
        this.hashTable = hashTable;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    
}
