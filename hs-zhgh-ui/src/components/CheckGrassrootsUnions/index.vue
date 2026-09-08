<template>
    <div>
        <el-select v-if="valType == 'ID'" v-model="union" placeholder="请选择基层工会" clearable @change="unionChange"
            style="width: 100%;" :disabled="disabled">
            <el-option label="暂无此工会" :value="union" v-if="hasIds.length == 0 && disabled=='disabled'"></el-option>
            <el-option v-for="item in unionOptions" :key="item.deptId" :label="item.deptName"
                :value="item.deptId"></el-option>
        </el-select>
        <el-select v-else-if="valType == 'NAME'" v-model="union" placeholder="请选择基层工会" clearable @change="unionChange" style="width: 100%;"
            :disabled="disabled">
            <el-option label="暂无此工会" :value="union" v-if="hasIds.length == 0 && disabled=='disabled'"></el-option>
            <el-option v-for="item in unionOptions" :key="item.deptId" :label="item.deptName"
                :value="item.deptName"></el-option>
        </el-select>
        <el-select v-else v-model="union" placeholder="请选择基层工会" clearable @change="unionChange" style="width: 100%;"
            :disabled="disabled">
            <el-option label="暂无此工会" :value="union" v-if="hasIds.length == 0 && disabled=='disabled'"></el-option>
            <el-option v-for="item in unionOptions" :key="item.deptId" :label="item.deptName"
                :value="item.deptId + '/--/' + item.deptName"></el-option>
        </el-select>
    </div>
</template>

<script>
// import {
//     listDept,

// } from "@/api/grassroots-trade-unions/grassroots-management";
import {
    getUnionOrUnit,

} from "@/api/grassroots-trade-unions/grassroots-management";
export default {
    name: "CheckGrassrootsUnions",
    props: {
        union: '',
        valType: {
            type: String,
            default: 'ID'
        },
        laborUnionType: {
            type: Number,
            default: 0
        },

        disabled: ''
    },
    data() {
        return {
            unionOptions: [],
            hasIds: [1],
        }
    },
    created() {
        this.getList()
    },
    methods: {
        getList() {
            let obj = {
                laborUnionType: this.laborUnionType
            }
            getUnionOrUnit(obj).then((response) => {
                this.unionOptions = response.data.map(item => {
                    return {
                        deptId: item.deptId,
                        deptName: item.deptName
                    }
                });

                if (this.union) {
                    this.hasIds = this.unionOptions.filter(item => item.deptId == this.union)
                } else {
                    this.hasIds = [1]
                }




            });
        },
        unionChange(val) {
            console.log(val);
            if (this.valType == 'NAME') {
                this.$emit('input', val)
            } else {
                this.$emit('input', val)
            }

        }
    }

}
</script>

<style></style>