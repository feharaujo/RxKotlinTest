package com.example.felipet.rxkotlintest.operators

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.example.felipet.rxkotlintest.R
import com.example.felipet.rxkotlintest.model.User
import com.example.felipet.rxkotlintest.utils.AppConstant
import com.example.felipet.rxkotlintest.utils.Utils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_zip.*

class ZipActivity : AppCompatActivity() {

    val TAG = "Juno"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zip)

        btn.setOnClickListener { doWork() }
    }

    private fun doWork() {
        Observable.zip(getCricketFansObservable(), getFootballFansObservable(),
                BiFunction<List<User>, List<User>, List<User>> { t1, t2 ->
                    Utils.filterUserWhoLovesBoth(t1, t2)
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver())
    }

    private fun getCricketFansObservable(): Observable<List<User>> {
        return Observable.create {
            if(!it.isDisposed) {
                it.onNext(Utils.userListWhoLovesCricket)
                it.onComplete()
            }
        }
    }

    private fun getFootballFansObservable(): Observable<List<User>> {
        return Observable.create {
            if(!it.isDisposed) {
                it.onNext(Utils.userListWhoLovesFootball)
                it.onComplete()
            }
        }
    }

    private fun getObserver(): Observer<List<User>> {
        return object: Observer<List<User>> {
            override fun onNext(userList: List<User>?) {
                textView.append(" onNext")
                textView.append(AppConstant.LINE_SEPARATOR)

                userList?.forEach {
                    textView.append(" firstname : " + it.firstname)
                    textView.append(AppConstant.LINE_SEPARATOR)
                }

                Log.d(TAG, " onNext : " + userList?.size)
            }

            override fun onError(e: Throwable?) {
                textView.append(" onError : " + e?.message)
                textView.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, " onError : " + e?.message)
            }

            override fun onSubscribe(d: Disposable?) {
                Log.d(TAG, " onSubscribe : " + d?.isDisposed)
            }

            override fun onComplete() {
                textView.append(" onComplete")
                textView.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, " onComplete")
            }

        }
    }
}
