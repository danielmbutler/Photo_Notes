class NotesViewModel(
    private val db: NotesDao,
) : ViewModel() {

    val notes: LiveData<List<Note>> = db.getNotes()


    private fun deleteNotes(note: Note) {
        viewModelscope.launch(Dispatches.IO){
            db.deleteNote(note)
        }
    }

    private fun updateNote(note: Note) {
        viewModelscope.launch(Dispatches.IO){
            db.updateNote(note)
        }
    }

    private fun createNote(title: String, note: String) {
        viewModelscope.launch(Dispatches.IO){
            db.createNote(Note(title = title, note = note))
        }
    }

}

class NotesViewModelFactory(
    private val db: NotesDap,
) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        NotesViewModel(
            db = db,
        ) as T

}