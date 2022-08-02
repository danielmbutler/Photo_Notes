class PhotoNotesApp : Application(){

    init {
        instance = this
        db = getDb()
    }

    private fun getDb = Room.databaseBuilder(
        applicationContext(),
        NotesDatabase::class.java, Constants.DATABASE_TITLE
    ).fallbackToDestructiveMigration()// remove in prod
        .build()


    companion object {
        private var instance: PhotoNotesApp? = null
        private var db : NotesDatabase? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }

        fun getDao() {
            return instance!!.db.NotesDao()
        }

    }


}