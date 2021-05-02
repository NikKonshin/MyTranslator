package com.nikitakonshin.mytranslator.view.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.nikitakonshin.mytranslator.MainActivity
import com.nikitakonshin.mytranslator.R
import com.nikitakonshin.mytranslator.utils.isOnline
import com.nikitakonshin.utils.AlertDialogFragment
import kotlinx.android.synthetic.main.fragment_description.*

private const val TITLE_EXTRA = "package com.nikitakonshin.mytranslator.view.fragments.title"
private const val TRANSLATE_EXTRA =
    "package com.nikitakonshin.mytranslator.view.fragments.translate"
private const val URI_EXTRA = "package com.nikitakonshin.mytranslator.view.fragments.uri"
private const val DIALOG_FRAGMENT_TAG = "8c7dff51-9769-4f6d-bbee-a3896085e76e"


class DescriptionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setActionbarHomeButtonAsUp()
        description_screen_swipe_refresh_layout.setOnRefreshListener { startLoadingOrShowError() }
        setData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                fragmentManager?.popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setData() {
        val word = arguments?.getString(TITLE_EXTRA)
        val translate = arguments?.getString(TRANSLATE_EXTRA)
        val imageUrl = arguments?.getString(URI_EXTRA)

        description_header.text = word
        description_textview.text = translate
        if (imageUrl.isNullOrBlank()) {
            stopRefreshAnimationIfNeeded()
        } else {
            useGlideToLoadPhoto(description_imageview, imageUrl)
        }
    }

    private fun useGlideToLoadPhoto(imageView: ImageView, imageUrl: String) {
        Glide.with(imageView.context!!)
            .load("https:$imageUrl")
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    stopRefreshAnimationIfNeeded()
                    imageView.setImageResource(R.drawable.ic_load_error_vector)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    stopRefreshAnimationIfNeeded()
                    return false
                }
            }).apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_no_photo_vector)
                    .centerCrop()
            ).into(imageView)
    }

    private fun stopRefreshAnimationIfNeeded() {
        if (description_screen_swipe_refresh_layout.isRefreshing) {
            description_screen_swipe_refresh_layout.isRefreshing = false
        }
    }

    private fun startLoadingOrShowError() {
        if (isOnline(context!!)) {
            setData()
        } else {
            AlertDialogFragment.newInstance(
                getString(R.string.dialog_title_device_is_offline),
                getString(R.string.dialog_message_device_is_offline)
            ).show(
                childFragmentManager,
                DIALOG_FRAGMENT_TAG
            )
            stopRefreshAnimationIfNeeded()
        }
    }

    private fun setActionbarHomeButtonAsUp() {
        val activity = activity as MainActivity
        activity.supportActionBar?.setHomeButtonEnabled(true)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        fun newInstance(word: String?, translate: String, imageUrl: String?): DescriptionFragment =
            DescriptionFragment().apply {
                arguments = Bundle().apply {
                    putString(TITLE_EXTRA, word)
                    putString(TRANSLATE_EXTRA, translate)
                    putString(URI_EXTRA, imageUrl)
                }
            }
    }
}