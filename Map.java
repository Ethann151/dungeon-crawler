public class Map {
   private Room[][] roomGrid;
      public int getNumRows() {return roomGrid.length;}
      public int getNumCols() {return roomGrid[0].length;}

   public Map(int rows, int columns) {
      roomGrid = new Room[rows][columns];
      populateMap();
   }
   
   public void populateMap() {
      for(int row = 0; row < roomGrid.length; row++) {
         for(int column = 0; column < roomGrid[0].length; column++) {
         roomGrid[row][column] = new Room();
         }
      }
   }
   
   public Room getRoom(int row, int column) {
      return roomGrid[row][column];
   }
   
   // Only used for testing
   public Room getRandomRoom() {
      int randRow = ((int) (roomGrid.length * Math.random()) + 1);
      int randColumn = ((int) (roomGrid[0].length * Math.random()) + 1);
      return roomGrid[randRow][randColumn];
   }
}