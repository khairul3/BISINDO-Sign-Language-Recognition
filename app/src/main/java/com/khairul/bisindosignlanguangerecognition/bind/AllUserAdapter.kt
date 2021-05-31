package com.khairul.bisindosignlanguangerecognition.bind

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.khairul.bisindosignlanguangerecognition.R
import com.khairul.bisindosignlanguangerecognition.data.source.entity.DictionaryEntity
import com.khairul.bisindosignlanguangerecognition.databinding.ItemRowsDictionaryBinding


//class AllUserAdapter(
////    private val context: getDictionaryData,
////    private val collection: CollectionReference,
//    options: FirestoreRecyclerOptions<DictionaryEntity>
//) : FirestoreRecyclerAdapter<DictionaryEntity, AllUserAdapter.UsersViewHolder>(options) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
//        return UsersViewHolder(
//            LayoutInflater.from(parent.context)
//                .inflate(R.layout.item_rows_dictionary, parent, false)
//        )
//    }
//
//    override fun onBindViewHolder(
//        viewHolder: UsersViewHolder,
//        position: Int,
//        users: DictionaryEntity
//    ) {
//        viewHolder.bindItem(users)
////        viewHolder.itemView.setOnClickListener {
////            showDialogMenu(users)
////        }
//    }
//
//    class UsersViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
//        val binding = ItemRowsDictionaryBinding.bind(view)
//        fun bindItem(users: DictionaryEntity) {
//            view.apply {
//                val huruf = "Huruf ${users.alphabet}"
//                val image = "${users.image}"
//
//                binding.txtTitle.text = huruf
//                Glide.with(itemView.context)
//                    .load(image)
//                    .into(binding.imgPoster)
//            }
//        }
//    }
//
////    private fun showDialogMenu(users: Users) {
////        //dialog popup edit hapus
////        val builder = AlertDialog.Builder(context, R.style.ThemeOverlay_MaterialComponents_Dialog_Alert)
////        val option = arrayOf("Edit", "Hapus")
////        builder.setItems(option) { dialog, which ->
////            when (which) {
////                //0 -> untuk berpindah ke activity AddEditActivity untuk edit dengan membawa data
////                0 -> context.startActivity(Intent(context, AddEditActivity::class.java).apply {
////                    putExtra(AddEditActivity.REQ_EDIT, true)
////                    putExtra(AddEditActivity.EXTRA_DATA, users)
////                })
////                1 -> showDialogDel(users.strId)
////            }
////        }
////        builder.create().show()
////    }
////    private fun showDialogDel(strId: String) {
////        //dialog pop delete
////        val builder = AlertDialog.Builder(context, R.style.ThemeOverlay_MaterialComponents_Dialog_Alert)
////            .setTitle("Hapus Data")
////            .setMessage("Yakin mau hapus?")
////            .setPositiveButton(android.R.string.yes) { dialog, which ->
////                deleteById(strId)
////            }
////            .setNegativeButton(android.R.string.cancel, null)
////        builder.create().show()
////    }
////    private fun deleteById(id: String) {
////
////        //menghapus data berdasarkan id
////        collection.document(id)
////            .delete()
////            .addOnCompleteListener { Toast.makeText(context, "Succes Hapus data", Toast.LENGTH_SHORT).show() }
////            .addOnFailureListener { Toast.makeText(context, "Gagal Hapus data", Toast.LENGTH_SHORT).show() }
////    }
//
//}