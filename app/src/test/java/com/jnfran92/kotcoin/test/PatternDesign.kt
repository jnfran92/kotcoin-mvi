package com.jnfran92.kotcoin.test

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait
import org.junit.Test


class PatternDesign {


    class Dialog (val builder: Builder){

        val dialogTitle = builder.title
        val dialogDescription = builder.description

        class Builder{

            lateinit var title: String
            lateinit var description: String

            fun addTitle(t: String): Builder{
                this.title = t
                return this
            }

            fun addDescription(d: String): Builder{
                this.description = d
                return this
            }

            fun build(): Dialog{
                return Dialog(this)
            }

        }

    }


    class Repository private constructor(val name: String){

        companion object{
            private var repoInstance: Repository? = null

            fun getInstance(name: String): Repository{
                if(repoInstance == null){
                    repoInstance = Repository(name)
                }
                return repoInstance!!
            }
        }
    }

    object DataSource{
        fun getDataAPI(){

        }
    }

    object RepositoryObject{
        fun getData(){
            DataSource.getDataAPI()
        }
    }

    interface MonoSource{
        fun soundMonoChannel()
    }

    interface StereoSource{
        fun soundLeftChannels()
        fun soundRightChannels()
    }

    class Walkman: MonoSource{
        override fun soundMonoChannel() {
            println("sound one channel")
        }
    }

    class IPod: StereoSource{
        override fun soundLeftChannels() {
            println("sound left channel")
        }

        override fun soundRightChannels() {
            println("sound right channel")
        }
    }

    class StereoToMonoAdapter(val stereoSource: StereoSource): MonoSource{
        override fun soundMonoChannel() {
            stereoSource.soundLeftChannels()
            stereoSource.soundRightChannels()
        }
    }


    @Test
    fun designPatterns(){

        val dialog = Dialog.Builder().addTitle("23").addDescription("12312").build()

        val singleton = Repository.getInstance("")

        val singleton2 = RepositoryObject


        val walkman = Walkman()
        val iPod = IPod()

        fun playMusicInMono(monoSource: MonoSource){
            monoSource.soundMonoChannel()
        }

        playMusicInMono(walkman)
        playMusicInMono(StereoToMonoAdapter(iPod))

    }


    suspend fun emitData(){
        repeat(5){
            println("emitting data start $it")
            delay(2000)
            println("emitting data end $it")
        }
    }

    @Test
    fun testCoroutines(){
        println("testCoroutines")

        runBlocking {
            emitData()
            println("Job ended!")

            println("Job started")
            val job = GlobalScope.launch {
                emitData()
            }

            delay(1000)
            job.cancel()


            delay(3000)
        }





        println("testCoroutines end")

    }


}