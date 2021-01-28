package com.example.myapplication.ui.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment {
    constructor() : super()
    constructor(@LayoutRes res: Int) : super(res)
}