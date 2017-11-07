package me.xandeer.examples.animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import kotlinx.android.synthetic.main.activity_property_animation.*
import me.xandeer.examples.BaseActivity
import me.xandeer.examples.R

class PropertyAnimationActivity : BaseActivity() {

  override fun setContentView() {
    setContentView(R.layout.activity_property_animation)
  }

  override fun initView() {
    translate.setOnClickListener {
      val go = ObjectAnimator.ofFloat(translate_view, "translationX", 0f, 500f)
      val back = ObjectAnimator.ofFloat(translate_view, "translationX", 500f, 0f)
      go.duration = 1000
      back.startDelay = 500
      back.duration = 1000

      val animatorSet = AnimatorSet()
      animatorSet.play(back).after(go)
      animatorSet.start()
    }

    fade.setOnClickListener {
      var dest = 1f
      if (fade_view.alpha > 0) {
        dest = 0f
      }
      val animation = ObjectAnimator.ofFloat(fade_view, "alpha", dest)
      animation.duration = 1000
      animation.start()
    }

    rotate.setOnClickListener {
      var dest = 360f
      if (rotate_view.rotation > 359) {
        dest = 0f
      }
      val animation = ObjectAnimator.ofFloat(rotate_view, "rotation", dest)
      animation.duration = 1000
      animation.start()
    }
  }
}
