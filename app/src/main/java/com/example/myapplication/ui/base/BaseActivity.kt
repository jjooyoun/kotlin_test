package com.example.myapplication.ui.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity {
    constructor() : super()
    constructor(@LayoutRes res: Int) : super(res)
}