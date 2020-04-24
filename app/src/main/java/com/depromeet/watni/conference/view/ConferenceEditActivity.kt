package com.depromeet.watni.conference.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.depromeet.watni.CROP_IMAGE
import com.depromeet.watni.PICK_FROM_ALBUM
import com.depromeet.watni.R
import com.depromeet.watni.REQUEST_IMAGE_CAPTURE
import com.depromeet.watni.base.BaseActivity
import com.depromeet.watni.base.CommonStatus
import com.depromeet.watni.conference.ConferenceViewModel
import com.depromeet.watni.conference.EditMode
import com.depromeet.watni.databinding.ActivityEditConferenceBinding
import com.depromeet.watni.ext.*
import com.depromeet.watni.listener.OnTimeRangeSelectedListener
import com.depromeet.watni.model.request.CreateConference
import com.depromeet.watni.ui.PickBottomSheetDialog
import com.depromeet.watni.ui.TimeRangePickerDialog
import com.depromeet.watni.ui.model.BottomSheetItemContent
import com.depromeet.watni.util.EditInputValidator
import com.depromeet.watni.util.FileUtil
import com.depromeet.watni.util.ResourceUtil


/*
 * Created by yunji on 19/04/2020
 */
class ConferenceEditActivity : BaseActivity<ActivityEditConferenceBinding>(R.layout.activity_edit_conference) {
    private val viewModel: ConferenceViewModel by viewModels { getViewModelFactory() }
    private var editMode = EditMode.NONE.code
    private var selectedImg: Bitmap? = null

    companion object {
        val TAG: String = ConferenceEditActivity::class.java.name

        fun getIntent(context: Context): Intent = Intent(context, ConferenceEditActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        editMode = intent.getIntExtra(EditMode.TAG, EditMode.NONE.code)
        initBinding()
        initView()
        observeUiData()
    }

    private fun initBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun initView() {
        if (editMode == EditMode.NEW.code) {
            binding.etTitle.requestFocus() // 새로 작성할 때만 키보드 바로 올라오게
        }

        binding.etTime.setOnClickListener {
            TimeRangePickerDialog.newInstance(object : OnTimeRangeSelectedListener {
                override fun onTimeSelected(startHour: Int, startMin: Int, endHour: Int, endMin: Int) {
                    binding.etTime.setTimeText(startHour, startMin, endHour, endMin)
                }
            }).show(supportFragmentManager)
        }
        binding.ivAddImage.setOnClickListener {
            showImgPickDialog()
        }
        binding.btnEnroll.setOnClickListener {
            viewModel.createConference(getConferenceData())
        }
    }

    private fun observeUiData() {
        with(viewModel) {
            commonResult.observe(this@ConferenceEditActivity, Observer {
                when (it.status) {
                    CommonStatus.SUCCESS -> {
                        setResult(Activity.RESULT_OK)
                        finish()
                    }
                    else -> {
                        // do nothing
                    }
                }
            })
            titleText.observe(this@ConferenceEditActivity, Observer {
                binding.layoutEtTitle.updateStatus(EditInputValidator.isValidTitle(it), R.string.conference_title_limit)
                updateAvailable()
            })
            placeText.observe(this@ConferenceEditActivity, Observer {
                binding.layoutEtTitle.updateStatus(it.isNotBlank(), R.string.conference_place_limit)
                updateAvailable()
            })
        }
    }

    private fun getConferenceData() = with(binding) {
        return@with CreateConference(
            selectedImg?.base64Encoding() ?: "",
            etTitle.text.toString(),
            etPlace.text.toString(),
            etTitle.text.toString(),
            etNotice.text.toString(),
            etTime.getStartTimestamp() + etDate.getTimestamp(),
            etTime.getEndTimestamp() + etDate.getTimestamp()
        )
    }

    override fun onBackPressed() {
        PickBottomSheetDialog.getInstance(
            ResourceUtil.getString(R.string.conference_delete_confirm),
            BottomSheetItemContent(ResourceUtil.getString(R.string.delete)) { super.onBackPressed() },
            BottomSheetItemContent(ResourceUtil.getString(R.string.edit_continue)) { it.dismiss() }
        ).show(this)
    }

    private fun showImgPickDialog() {
        PickBottomSheetDialog.getInstance(
            ResourceUtil.getString(R.string.conference_img_example_upload),
            BottomSheetItemContent(ResourceUtil.getString(R.string.camera)) {
                dispatchTakePictureIntent()
                it.dismiss()
            },
            BottomSheetItemContent(ResourceUtil.getString(R.string.gallery)) {
                dispatchOpenGalleryIntent()
                it.dismiss()
            }
        ).show(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK) {
            return
        }

        when (requestCode) {
            REQUEST_IMAGE_CAPTURE -> {
                selectedImg = data?.extras?.get("data") as Bitmap?
                binding.ivAddImage.setFullImage(selectedImg)
            }
            PICK_FROM_ALBUM -> {
                data?.data?.let {
                    selectedImg = FileUtil.uriToBitmap(it, this)
                    binding.ivAddImage.setFullImage(selectedImg)
                }
            }
            CROP_IMAGE -> {
                // TODO : 비율 맞춰 자르는 화면으로 이동
            }
        }
    }
}