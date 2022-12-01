# add和commit
工作区 -> 暂存区 -> 本地库

add是暂存区，commit是本地库

# 日志
git log --pretty=oneline
git log --oneline
git reflog

# reset
rest：回退或者前进移动git指针
git reset --hard c6d390d44034cd48dd8c625e68beef5c0292053b
--hard：工作区、暂存区、本地库都移动。用的最多的就是hard，否则你不知道那些文件发生了移动
--mixed:工作区不动，暂存区、本地库都移动
--soft：工作区、暂存区不动，本地库移动

# 删除
