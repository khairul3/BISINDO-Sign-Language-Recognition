package com.khairul.bisindosignlanguangerecognition.data.firestore

//class getDictionaryData : Fragment() {
//    private lateinit var binding: FragmentDictionaryBinding
//    private lateinit var mAdapter: FirestoreRecyclerAdapter<DictionaryEntity, AllUserAdapter.UsersViewHolder>
//    private val mFirestore = FirebaseFirestore.getInstance()
//    private val mUsersCollection = mFirestore.collection(ALPHABET)
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        initView()
//        setupAdapter()
//
//    }
//
//    private fun setupAdapter() {
//        val options = FirestoreRecyclerOptions.Builder<DictionaryEntity>()
//            .build()
//
//        mAdapter = AllUserAdapter(options)
//        mAdapter.notifyDataSetChanged()
//        binding.rvDic.adapter = mAdapter
//
//    }
//
//    private fun initView() {
//        with(binding.rvDic) {
//            layoutManager = LinearLayoutManager(context)
//            setHasFixedSize(true)
//            layoutManager = LinearLayoutManager(context)
//        }
//    }
//
//}