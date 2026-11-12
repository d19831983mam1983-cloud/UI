package com.znak.launcher
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
class AppRepository(private val context: Context) {
    fun loadApps(): List<AppInfo> {
        val pm = context.packageManager
        val intent = Intent(Intent.ACTION_MAIN, null).addCategory(Intent.CATEGORY_LAUNCHER)
        val resolveInfos = pm.queryIntentActivities(intent, PackageManager.MATCH_ALL)
        val ownPackage = context.packageName
        return resolveInfos
            .asSequence()
            .filter { it.activityInfo.packageName != ownPackage }
            .map { it.activityInfo }
            .distinctBy { it.packageName }
            .map { activityInfo ->
                AppInfo(
                    label = activityInfo.loadLabel(pm).toString(),
                    packageName = activityInfo.packageName,
                    icon = activityInfo.loadIcon(pm)
                )
            }
            .sortedBy { it.label.lowercase() }
            .toList()
    }
    fun launchApp(packageName: String): Boolean {
        val intent = context.packageManager.getLaunchIntentForPackage(packageName) ?: return false
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED)
        context.startActivity(intent)
        return true
    }
}
