@Database(entities = [
  Note::class], version = 1
  @TypeConverters(Converters::class
abstract class NotesDatabase : RoomDatabase() {
    abstract fun NotesDao(): NotesDao

}