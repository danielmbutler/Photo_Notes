@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes WHERE Note.id=:id")
    fun getNoteById(id: Int) : Note

    @Query("SELECT * FROM Notes ORDER BY dateCreated DESC")
    fun getNotes() : LiveData<List<Note>>

    @Delete
    fun deleteNote(note: Note) : Int

    @Update
    fun updateNote(note: Note) : Int

    @Query("SELECT * FROM Notes WHERE title LIKE '%' || :query || '%' OR  note LIKE '%' || :query || '%' ")
    fun queryNotesByTitleAndContent(query: String) : List<Note>

    @Insert()
    fun insertNote(note: Note) : Int

}